/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.type.define;

public enum Cardinality implements SerialEnum {

    /**
     * Only a single value may be associated with the given key.
     */
    SINGLE(1, "single"),

    /**
     * Multiple values and duplicate values may be associated with the given
     * key.
     */
    LIST(2, "list"),

    /**
     * Multiple but distinct values may be associated with the given key.
     */
    SET(3, "set");

    private byte code = 0;
    private String name = null;

    static {
        SerialEnum.register(Cardinality.class);
    }

    Cardinality(int code, String name) {
        assert code < 256;
        this.code = (byte) code;
        this.name = name;
    }

    @Override
    public byte code() {
        return this.code;
    }

    public String string() {
        return this.name;
    }
}