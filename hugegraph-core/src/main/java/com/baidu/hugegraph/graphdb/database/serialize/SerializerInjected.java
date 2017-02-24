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

package com.baidu.hugegraph.graphdb.database.serialize;

/**
 * Marks a {@link com.baidu.hugegraph.core.attribute.AttributeSerializer} that requires a
 * {@link com.baidu.hugegraph.graphdb.database.serialize.Serializer} to serialize the internal state. It is expected
 * that the serializer is passed into this object upon initialization and before usage. Furthermore, such serializers
 * will convert the {@link com.baidu.hugegraph.diskstorage.WriteBuffer} passed into the
 * {@link com.baidu.hugegraph.core.attribute.AttributeSerializer}'s write methods to be cast to
 * {@link com.baidu.hugegraph.graphdb.database.serialize.DataOutput}.
 *
 * @author Matthias Broecheler (me@matthiasb.com)
 */
public interface SerializerInjected {

    public void setSerializer(Serializer serializer);

}