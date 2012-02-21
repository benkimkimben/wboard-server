/*
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wboard.server.ipc;

import org.apache.hadoop.io.Writable;

import com.wboard.common.protocol.ClientProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;

/** 
 * An abstract IPC service.  IPC calls take a single {@link Writable} as a
 * parameter, and return a {@link Writable} as their value.  A service runs on
 * a port and is defined by a parameter class and a value class. 
 * 
 * @see org.apache.hadoop.hbase.ipc.RpcServer
 */
public interface RpcServerProtocol {

	void setSocketSendBufSize(int size);

	void start();

	void stop();

	void join() throws InterruptedException;

	InetSocketAddress getListenerAddress();

	/** Called for each call.
	 * @param param writable parameter
	 * @param receiveTime time
	 * @return Writable
	 * @throws java.io.IOException e
	 */
	Writable call(Class<? extends ClientProtocol> protocol, Writable param, long receiveTime) throws IOException;

	int getNumOpenConnections();

	int getCallQueueLen();

	void setErrorHandler(RPCErrorHandler handler);

	void openServer();

	void startThreads();

}
