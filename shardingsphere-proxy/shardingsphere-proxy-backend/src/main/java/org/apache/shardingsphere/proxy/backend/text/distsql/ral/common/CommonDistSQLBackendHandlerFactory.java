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

package org.apache.shardingsphere.proxy.backend.text.distsql.ral.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.distsql.parser.statement.ral.CommonDistSQLStatement;
import org.apache.shardingsphere.distsql.parser.statement.ral.common.HintDistSQLStatement;
import org.apache.shardingsphere.distsql.parser.statement.ral.common.RefreshTableMetadataStatement;
import org.apache.shardingsphere.distsql.parser.statement.ral.common.SetDistSQLStatement;
import org.apache.shardingsphere.distsql.parser.statement.ral.common.ShowDistSQLStatement;
import org.apache.shardingsphere.distsql.parser.statement.ral.common.drop.DropTrafficRuleStatement;
import org.apache.shardingsphere.proxy.backend.session.ConnectionSession;
import org.apache.shardingsphere.proxy.backend.text.TextProtocolBackendHandler;
import org.apache.shardingsphere.proxy.backend.text.distsql.ral.common.drop.DropTrafficRuleHandler;

import java.sql.SQLException;

/**
 * Common dist sql backend handler factory.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonDistSQLBackendHandlerFactory {
    
    /**
     * Create new instance of common dist sql backend handler.
     * 
     * @param sqlStatement common dist sql statement
     * @param connectionSession connection session
     * @return common dist sql backend handler
     * @throws SQLException SQL exception
     */
    public static TextProtocolBackendHandler newInstance(final CommonDistSQLStatement sqlStatement, final ConnectionSession connectionSession) throws SQLException {
        if (sqlStatement instanceof SetDistSQLStatement) {
            return new SetDistSQLBackendHandler((SetDistSQLStatement) sqlStatement, connectionSession);
        }
        if (sqlStatement instanceof ShowDistSQLStatement) {
            return new ShowDistSQLBackendHandler((ShowDistSQLStatement) sqlStatement, connectionSession);
        }
        if (sqlStatement instanceof HintDistSQLStatement) {
            return new HintDistSQLBackendHandler((HintDistSQLStatement) sqlStatement, connectionSession);
        }
        if (sqlStatement instanceof RefreshTableMetadataStatement) {
            return new RefreshTableMetadataHandler((RefreshTableMetadataStatement) sqlStatement, connectionSession);
        }
        if (sqlStatement instanceof DropTrafficRuleStatement) {
            return new DropTrafficRuleHandler((DropTrafficRuleStatement) sqlStatement);
        }
        return null;
    }
}
