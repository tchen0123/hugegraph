package com.baidu.hugegraph2.schema.base.maker;

import com.baidu.hugegraph2.schema.base.structure.PropertyKey;

/**
 * Created by jishilei on 17/3/17.
 */
public interface PropertyKeyMaker extends SchemaMaker {

    public PropertyKeyMaker Text();
    public PropertyKeyMaker Int();


}
