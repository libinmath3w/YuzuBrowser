/*
 * Copyright (C) 2017 Hazuki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.hazuki.yuzubrowser.action.item

import android.app.AlertDialog
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import jp.hazuki.yuzubrowser.R
import jp.hazuki.yuzubrowser.action.SingleAction
import jp.hazuki.yuzubrowser.action.view.ActionActivity
import jp.hazuki.yuzubrowser.utils.app.StartActivityInfo
import java.io.IOException

class ToastAction : SingleAction, Parcelable {

    var text: String? = null
        private set

    @Throws(IOException::class)
    constructor(id: Int, parser: JsonParser?) : super(id) {

        if (parser != null) {
            if (parser.nextToken() != JsonToken.START_OBJECT) return
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                if (parser.currentToken != JsonToken.FIELD_NAME) return
                if (FIELD_TEXT == parser.currentName) {
                    if (parser.nextToken() == JsonToken.VALUE_STRING)
                        text = parser.text
                    continue
                }
                parser.skipChildren()
            }
        }
    }

    @Throws(IOException::class)
    override fun writeIdAndData(generator: JsonGenerator) {
        generator.writeNumber(id)
        generator.writeStartObject()
        generator.writeStringField(FIELD_TEXT, text)
        generator.writeEndObject()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(text)
    }

    private constructor(source: Parcel) : super(source.readInt()) {
        text = source.readString()
    }

    override fun showMainPreference(context: ActionActivity): StartActivityInfo? {
        return showSubPreference(context)
    }

    override fun showSubPreference(context: ActionActivity): StartActivityInfo? {
        val v = View.inflate(context, R.layout.action_toast_dialog, null)
        val editText = v.findViewById<EditText>(R.id.editText)

        editText.setText(text)

        AlertDialog.Builder(context)
                .setTitle(R.string.action_settings)
                .setView(v)
                .setPositiveButton(android.R.string.ok) { _, _ -> text = editText.text.toString() }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        return null
    }

    companion object {
        private const val FIELD_TEXT = "0"

        @JvmField
        val CREATOR: Parcelable.Creator<ToastAction> = object : Parcelable.Creator<ToastAction> {
            override fun createFromParcel(source: Parcel): ToastAction {
                return ToastAction(source)
            }

            override fun newArray(size: Int): Array<ToastAction?> {
                return arrayOfNulls(size)
            }
        }
    }
}
