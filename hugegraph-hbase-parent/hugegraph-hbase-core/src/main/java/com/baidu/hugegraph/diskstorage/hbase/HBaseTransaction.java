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

package com.baidu.hugegraph.diskstorage.hbase;

import com.baidu.hugegraph.diskstorage.BaseTransactionConfig;
import com.baidu.hugegraph.diskstorage.common.AbstractStoreTransaction;

/**
 * This class overrides and adds nothing compared with
 * {@link com.baidu.hugegraph.diskstorage.locking.consistentkey.ExpectedValueCheckingTransaction}; however, it creates a
 * transaction type specific to HBase, which lets us check for user errors like passing a Cassandra transaction into a
 * HBase method.
 *
 * @author Dan LaRocque <dalaro@hopcount.org>
 */
public class HBaseTransaction extends AbstractStoreTransaction {

    public HBaseTransaction(final BaseTransactionConfig config) {
        super(config);
    }
}