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

package com.baidu.hugegraph.core.log;

/**
 * Identifies the type of change has undergone. Used in {@link ChangeState} to retrieve those elements that have been
 * changed in a certain way.
 * <p/>
 * {@link #ADDED} applies to elements that have been added to the graph, {@link #REMOVED} is for removed elements, and
 * {@link #ANY} is used to retrieve all elements that have undergone change.
 * <p/>
 * {@link #ADDED} and {@link #REMOVED} are considered proper change states.
 *
 * @author Matthias Broecheler (me@matthiasb.com)
 */
public enum Change {

    ADDED, REMOVED, ANY;

    public boolean isProper() {
        switch (this) {
            case ADDED:
            case REMOVED:
                return true;
            default:
                return false;
        }
    }

}