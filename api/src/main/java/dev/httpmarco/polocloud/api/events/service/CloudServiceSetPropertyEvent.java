/*
 * Copyright 2024 Mirco Lindenau | HttpMarco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.httpmarco.polocloud.api.events.service;

import dev.httpmarco.polocloud.api.properties.Property;
import dev.httpmarco.polocloud.api.services.CloudService;

public final class CloudServiceSetPropertyEvent extends AbstractCloudServicePropertyEvent {

    public CloudServiceSetPropertyEvent(CloudService cloudService, Property<?> property, Object value) {
        super(cloudService, property, value);
    }
}
