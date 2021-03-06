/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.jfoenix.controls;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.skins.JFXColorPickerSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.List;

/**
 * JFXColorPicker is the metrial design implementation of color picker.
 *
 * @author Shadi Shaheen
 * @version 1.0
 * @since 2016-03-09
 */
public class JFXColorPicker extends ColorPicker {

    private final List<Color> listedColors;

    private final StringProperty recentColorsText = new SimpleStringProperty(this, "recentColorsText", "Recent Colors");
    private final StringProperty customColorText = new SimpleStringProperty(this, "customColorText", "Custom Color");

    /**
     * {@inheritDoc}
     */
    public JFXColorPicker() {
        this.listedColors = null;

        initialize();
    }

    /**
     * {@inheritDoc}
     */
    public JFXColorPicker(Color color) {
        super(color);
        this.listedColors = null;

        initialize();
    }

    /**
     * {@inheritDoc}
     */
    public JFXColorPicker(Color color, List<Color> listedColors) {
        super(color);
        this.listedColors = listedColors;

        initialize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new JFXColorPickerSkin(this, listedColors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserAgentStylesheet() {
        return JFoenixResources.load("/css/controls/jfx-color-picker.css").toExternalForm();
    }

    private void initialize() {
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public String getRecentColorsText() {
        return recentColorsText.get();
    }

    public StringProperty recentColorsTextProperty() {
        return recentColorsText;
    }

    public void setRecentColorsText(String recentColorsText) {
        this.recentColorsText.set(recentColorsText);
    }

    public String getCustomColorText() {
        return customColorText.get();
    }

    public StringProperty customColorTextProperty() {
        return customColorText;
    }

    public void setCustomColorText(String customColorText) {
        this.customColorText.set(customColorText);
    }

    /**
     * Initialize the style class to 'jfx-color-picker'.
     * <p>
     * This is the selector class from which CSS can be used to style
     * this control.
     */
    private static final String DEFAULT_STYLE_CLASS = "jfx-color-picker";

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        setNeedsLayout(false);
    }
}
