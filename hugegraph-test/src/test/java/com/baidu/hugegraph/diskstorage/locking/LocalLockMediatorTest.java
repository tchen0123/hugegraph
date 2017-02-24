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

package com.baidu.hugegraph.diskstorage.locking;

import com.baidu.hugegraph.diskstorage.util.time.TimestampProvider;
import com.baidu.hugegraph.diskstorage.util.time.TimestampProviders;
import com.baidu.hugegraph.diskstorage.StaticBuffer;
import com.baidu.hugegraph.diskstorage.locking.consistentkey.ExpectedValueCheckingTransaction;
import com.baidu.hugegraph.diskstorage.util.KeyColumn;
import com.baidu.hugegraph.diskstorage.util.StaticArrayBuffer;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class LocalLockMediatorTest {

    private static final String LOCK_NAMESPACE = "test";
    private static final StaticBuffer LOCK_ROW = StaticArrayBuffer.of(new byte[] { 1 });
    private static final StaticBuffer LOCK_COL = StaticArrayBuffer.of(new byte[] { 1 });
    private static final KeyColumn kc = new KeyColumn(LOCK_ROW, LOCK_COL);
    // private static final long LOCK_EXPIRATION_TIME_MS = 1;
    // private static final long SLEEP_MS = LOCK_EXPIRATION_TIME_MS * 1000;
    private static final ExpectedValueCheckingTransaction mockTx1 = mock(ExpectedValueCheckingTransaction.class);
    private static final ExpectedValueCheckingTransaction mockTx2 = mock(ExpectedValueCheckingTransaction.class);

    @Test
    public void testLockExpiration() throws InterruptedException {
        TimestampProvider times = TimestampProviders.MICRO;
        LocalLockMediator<ExpectedValueCheckingTransaction> llm =
                new LocalLockMediator<ExpectedValueCheckingTransaction>(LOCK_NAMESPACE, times);

        assertTrue(llm.lock(kc, mockTx1, Instant.EPOCH));
        assertTrue(llm.lock(kc, mockTx2, Instant.MAX));

        llm = new LocalLockMediator<ExpectedValueCheckingTransaction>(LOCK_NAMESPACE, times);

        assertTrue(llm.lock(kc, mockTx1, Instant.MAX));
        assertFalse(llm.lock(kc, mockTx2, Instant.MAX));
    }
}