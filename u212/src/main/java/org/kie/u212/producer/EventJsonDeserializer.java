/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.u212.producer;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.kie.u212.model.EventWrapper;
import org.kie.u212.model.EventWrapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventJsonDeserializer implements Deserializer<EventWrapperImpl> {

  private Logger logger = LoggerFactory.getLogger(EventJsonDeserializer.class);

  private ObjectMapper objectMapper;

  @Override
  public void configure(Map configs,
                        boolean isKey) {
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public EventWrapperImpl deserialize(String s,
                                    byte[] data) {
    try {
      return objectMapper.readValue(data,
                                    EventWrapperImpl.class);
    } catch (IOException e) {
      logger.error(e.getMessage(),
                   e);
    }
    return null;
  }

  @Override
  public void close() {
  }
}
