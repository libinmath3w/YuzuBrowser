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

package jp.hazuki.yuzubrowser.toolbar.main

import android.content.Context
import android.util.AttributeSet
import jp.hazuki.yuzubrowser.R
import jp.hazuki.yuzubrowser.action.manager.ActionController
import jp.hazuki.yuzubrowser.action.manager.ActionIconManager
import jp.hazuki.yuzubrowser.toolbar.BrowserToolbarManager
import jp.hazuki.yuzubrowser.utils.view.swipebutton.SwipeTextButton

class WhiteUrlBar(context: Context, controller: ActionController, iconManager: ActionIconManager, request_callback: BrowserToolbarManager.RequestCallback) : UrlBarBase(context, controller, iconManager, R.layout.toolbar_url_white, request_callback)

class WhiteSwipeTextButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : SwipeTextButton(context, attrs) {
    override fun onEventOutSide() = false

    override fun onEventCancel() = false

    override fun onEventActionUp(whatNo: Int) = false

    override fun onEventActionDown() = false
}