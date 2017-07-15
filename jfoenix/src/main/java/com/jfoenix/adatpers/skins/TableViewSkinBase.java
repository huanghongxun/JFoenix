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
package com.jfoenix.adatpers.skins;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import javafx.scene.control.Control;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.TableColumnBase;

/**
 * Why removed param B?
 * Java 9 does not open behaviors API, and TableViewSkinBase is declared as {@code TableSkinBase<M, S, C, I, TC>}.
 */
public abstract class TableViewSkinBase<M, S, C extends Control, I extends IndexedCell<M>, TC extends TableColumnBase<S, ?>> extends com.sun.javafx.scene.control.skin.TableViewSkinBase<M, S, C, BehaviorBase<C>, I, TC> {

    public TableViewSkinBase(C control, BehaviorBase<C> behavior) {
        super(control, behavior);
    }
}
