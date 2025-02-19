/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.index;

import java.io.IOException;
import org.apache.lucene.index.MergePolicy.MergeSpecification;
import org.apache.lucene.tests.index.BaseMergePolicyTestCase;
import org.apache.lucene.tests.util.TestUtil;

public class TestUpgradeIndexMergePolicy extends BaseMergePolicyTestCase {

  @Override
  public MergePolicy mergePolicy() {
    TieredMergePolicy in = newTieredMergePolicy();
    // Avoid low values of the max merged segment size which prevent this merge policy from
    // scaling well
    ((TieredMergePolicy) in).setMaxMergedSegmentMB(TestUtil.nextInt(random(), 1024, 10 * 1024));
    return new UpgradeIndexMergePolicy(in);
  }

  @Override
  protected void assertSegmentInfos(MergePolicy policy, SegmentInfos infos) throws IOException {
    // no-op
  }

  @Override
  protected void assertMerge(MergePolicy policy, MergeSpecification merge) throws IOException {
    // no-op
  }
}
