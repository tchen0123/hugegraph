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

package com.baidu.hugegraph.graphdb.internal;

import com.baidu.hugegraph.core.PropertyKey;
import com.baidu.hugegraph.core.HugeGraphRelation;

/**
 * Internal Relation interface adding methods that should only be used by HugeGraph.
 *
 * The "direct" qualifier in the method names indicates that the corresponding action is executed on this relation
 * object and not migrated to a different transactional context. It also means that access returns the "raw" value of
 * what is stored on this relation
 *
 * @author Matthias Broecheler (me@matthiasb.com)
 */
public interface InternalRelation extends HugeGraphRelation, InternalElement {

    /**
     * Returns this relation in the current transactional context
     *
     * @return
     */
    @Override
    public InternalRelation it();

    /**
     * Returns the vertex at the given position (0=OUT, 1=IN) of this relation
     * 
     * @param pos
     * @return
     */
    public InternalVertex getVertex(int pos);

    /**
     * Number of vertices on this relation.
     *
     * @return
     */
    public int getArity();

    /**
     * Number of vertices on this relation that are aware of its existence. This number will differ from
     * {@link #getArity()}
     *
     */
    public int getLen();

    public <O> O getValueDirect(PropertyKey key);

    public void setPropertyDirect(PropertyKey key, Object value);

    public Iterable<PropertyKey> getPropertyKeysDirect();

    public <O> O removePropertyDirect(PropertyKey key);

}