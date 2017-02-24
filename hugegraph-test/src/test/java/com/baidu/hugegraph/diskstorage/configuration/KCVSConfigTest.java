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

package com.baidu.hugegraph.diskstorage.configuration;

import com.baidu.hugegraph.diskstorage.BackendException;
import com.baidu.hugegraph.diskstorage.util.time.TimestampProviders;
import com.baidu.hugegraph.diskstorage.configuration.backend.KCVSConfiguration;
import com.baidu.hugegraph.diskstorage.keycolumnvalue.KeyColumnValueStoreManager;
import com.baidu.hugegraph.diskstorage.keycolumnvalue.StoreTransaction;
import com.baidu.hugegraph.diskstorage.keycolumnvalue.inmemory.InMemoryStoreManager;
import com.baidu.hugegraph.diskstorage.util.BackendOperation;
import com.baidu.hugegraph.diskstorage.util.StandardBaseTransactionConfig;
import com.baidu.hugegraph.graphdb.configuration.GraphDatabaseConfiguration;

/**
 * @author Matthias Broecheler (me@matthiasb.com)
 */
public class KCVSConfigTest extends WritableConfigurationTest {

    @Override
    public WriteConfiguration getConfig() {
        final KeyColumnValueStoreManager manager = new InMemoryStoreManager(Configuration.EMPTY);
        ModifiableConfiguration config = GraphDatabaseConfiguration.buildGraphConfiguration();
        config.set(GraphDatabaseConfiguration.TIMESTAMP_PROVIDER, TimestampProviders.MICRO);
        try {
            return new KCVSConfiguration(new BackendOperation.TransactionalProvider() {
                @Override
                public StoreTransaction openTx() throws BackendException {
                    return manager.beginTransaction(StandardBaseTransactionConfig.of(TimestampProviders.MICRO,
                            manager.getFeatures().getKeyConsistentTxConfig()));
                }

                @Override
                public void close() throws BackendException {
                    manager.close();
                }
            }, config, manager.openDatabase("hugegraph"), "general");
        } catch (BackendException e) {
            throw new RuntimeException(e);
        }
    }
}