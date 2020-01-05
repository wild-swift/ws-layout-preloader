/*
 * Copyright (C) 2018 Wild Swift
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
package name.wildswift.testapp;

import android.app.Activity;
import android.os.Bundle;

import java.util.Locale;

import name.wildswift.viewpreinflater.ViewInflater;
import name.wildswift.viewpreinflater.ViewInflaterInitCallback;
import name.wildswift.viewpreinflater.config.ViewInflaterConfig;
import name.wildswift.viewpreinflater.config.qualifiers.Density;
import name.wildswift.viewpreinflater.config.qualifiers.Keyboard;
import name.wildswift.viewpreinflater.config.qualifiers.KeysHidden;
import name.wildswift.viewpreinflater.config.qualifiers.LayoutDirection;
import name.wildswift.viewpreinflater.config.qualifiers.Navigation;
import name.wildswift.viewpreinflater.config.qualifiers.NightMode;
import name.wildswift.viewpreinflater.config.qualifiers.Orientation;
import name.wildswift.viewpreinflater.config.qualifiers.ScreenLong;
import name.wildswift.viewpreinflater.config.qualifiers.Touchscreen;
import name.wildswift.viewpreinflater.config.qualifiers.UiMode;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInflaterConfig config = ViewInflaterConfig.builder()
                .view(TestView.class).single()
                .view(TestView.class).forMnc(210).single()
                .view(TestView.class).forMcc(320).single()
                .view(TestView.class).forLocale(new Locale("es")).single()
                .view(TestView.class).forOrientation(Orientation.LANDSCAPE).single()
                .view(TestView.class).forTouchscreen(Touchscreen.NOTOUCH).single()
                .view(TestView.class).forDensity(Density.HIGH).single()
                .view(TestView.class).forKeyboard(Keyboard.K12KEY).single()
                .view(TestView.class).forNavigation(Navigation.WHEEL).single()
                .view(TestView.class).forKeysHidden(KeysHidden.YES).single()
                .view(TestView.class).forUiMode(UiMode.TELEVISION).single()
                .view(TestView.class).forNightMode(NightMode.YES).single()
                .view(TestView.class).forScreenLong(ScreenLong.YES).single()
                .view(TestView.class).forLayoutDirection(LayoutDirection.RTL).single()
                .view(TestView.class).forScreenWidth(420).single()
                .view(TestView.class).forScreenHeight(900).single()
                .view(TestView.class).forSmallestScreenWidth(500).single()
                .build();

        ViewInflater viewInflater = new ViewInflater(this, config);
        viewInflater.initialize(new ViewInflaterInitCallback() {
            @Override
            public void onInit(ViewInflater inflater) {
                System.out.println("test");
            }
        });
    }
}
