/**
 * Copyright 2010 JBoss Inc
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
package model;

import com.google.common.base.Optional;

/**
 * @author xiabai
 * 
 */
public class Product {
    private final Optional<Integer> id;

    private final String name;

    private final String description;

    public Product(int id, String name, String description) {
        this.id = Optional.of(id);
        this.name = name;
        this.description = description;
    }

    /**
     * @return An {@link Optional} {@code Integer} identifying this {@link Product}.
     */
    public Optional<Integer> getID() {
        return id;
    }

    /**
     * @return A {@code String} naming this {@link Product}.
     */
    public String getName() {
        return name;
    }

    /**
     * @return A {@code String} describing this {@link Product}.
     */
    public String getDescription() {
        return description;
    }

}
