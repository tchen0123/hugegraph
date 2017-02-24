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

package com.baidu.hugegraph.core;

import com.google.common.collect.ImmutableList;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Iterator;
import java.util.List;

/**
 * A HugeGraphEdge connects two {@link HugeGraphVertex}. It extends the functionality provided by Blueprint's
 * {@link Edge} and is a special case of a {@link HugeGraphRelation}.
 *
 * @author Matthias Br&ouml;cheler (http://www.matthiasb.com)
 * @see Edge
 * @see HugeGraphRelation
 * @see EdgeLabel
 */
public interface HugeGraphEdge extends HugeGraphRelation, Edge {

    /**
     * Returns the edge label of this edge
     *
     * @return edge label of this edge
     */
    public default EdgeLabel edgeLabel() {
        assert getType() instanceof EdgeLabel;
        return (EdgeLabel) getType();
    }

    /**
     * Returns the vertex for the specified direction. The direction cannot be Direction.BOTH.
     *
     * @return the vertex for the specified direction
     */
    public HugeGraphVertex vertex(Direction dir);

    @Override
    public default HugeGraphVertex outVertex() {
        return vertex(Direction.OUT);
    }

    @Override
    public default HugeGraphVertex inVertex() {
        return vertex(Direction.IN);
    }

    /**
     * Returns the vertex at the opposite end of the edge.
     *
     * @param vertex vertex on which this edge is incident
     * @return The vertex at the opposite end of the edge.
     * @throws InvalidElementException if the edge is not incident on the specified vertex
     */
    public HugeGraphVertex otherVertex(Vertex vertex);

    @Override
    public default Iterator<Vertex> vertices(Direction direction) {
        List<Vertex> vertices;
        if (direction == Direction.BOTH) {
            vertices = ImmutableList.of((Vertex) vertex(Direction.OUT), vertex(Direction.IN));
        } else {
            vertices = ImmutableList.of((Vertex) vertex(direction));
        }
        return vertices.iterator();
    }

}