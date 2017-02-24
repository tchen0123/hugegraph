// Copyright 2017 HugeGraph Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.baidu.hugegraph.core.schema;

import com.google.common.base.Preconditions;
import com.baidu.hugegraph.diskstorage.indexing.KeyInformation;
import com.baidu.hugegraph.graphdb.types.ParameterType;

/**
 * Used to change the default mapping of an indexed key by providing the mapping explicitly as a parameter to
 * {@link HugeGraphManagement#addIndexKey(HugeGraphIndex, com.baidu.hugegraph.core.PropertyKey, Parameter[])}.
 * <p/>
 * This applies mostly to string data types of keys, where the mapping specifies whether the string value is tokenized
 * ({@link #TEXT}) or indexed as a whole ({@link #STRING}), or both ({@link #TEXTSTRING}).
 *
 * @author Matthias Broecheler (me@matthiasb.com)
 */
public enum Mapping {

    DEFAULT, TEXT, STRING, TEXTSTRING;

    /**
     * Returns the mapping as a parameter so that it can be passed to
     * {@link HugeGraphManagement#addIndexKey(HugeGraphIndex, com.baidu.hugegraph.core.PropertyKey, Parameter[])}
     * 
     * @return
     */
    public Parameter asParameter() {
        return ParameterType.MAPPING.getParameter(this);
    }

    // ------------ USED INTERNALLY -----------

    public static Mapping getMapping(KeyInformation information) {
        Object value = ParameterType.MAPPING.findParameter(information.getParameters(), null);
        if (value == null)
            return DEFAULT;
        else {
            Preconditions.checkArgument((value instanceof Mapping || value instanceof String),
                    "Invalid mapping specified: %s", value);
            if (value instanceof String) {
                value = Mapping.valueOf(value.toString().toUpperCase());
            }
            return (Mapping) value;
        }
    }

    public static Mapping getMapping(String store, String key, KeyInformation.IndexRetriever informations) {
        KeyInformation ki = informations.get(store, key);
        Preconditions.checkArgument(ki != null, "Could not find key information for: %s", key);
        return getMapping(ki);
    }

}