/*
 * Copyright (c) 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.googleapis.json;

import com.google.api.client.http.HttpContent;
import com.google.api.client.json.Json;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Serializes JSON content based on the data key/value mapping object for an
 * item.
 * 
 * @since 2.2
 * @author Yaniv Inbar
 */
public final class JsonContent implements HttpContent {
  // TODO: ability to annotate fields as only needed for POST?

  public Object item;

  public final long getLength() {
    // TODO
    return -1;
  }

  public String getEncoding() {
    return null;
  }

  public String getType() {
    return Json.CONTENT_TYPE;
  }

  public void writeTo(OutputStream out) throws IOException {
    JsonGenerator generator =
        Json.JSON_FACTORY.createJsonGenerator(out, JsonEncoding.UTF8);
    generator.writeStartObject();
    generator.writeFieldName("data");
    Json.serialize(generator, this.item);
    generator.writeEndObject();
    generator.close();
  }
}