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
public class Bug {
    private final Optional<Integer> id;

    private Optional<String> priority = Optional.absent();

    private Optional<String> severity = Optional.absent();

    private Optional<String> alias = Optional.absent();

    private Optional<String> summary = Optional.absent();

    private Optional<Product> product = Optional.absent();

    private Optional<String> component = Optional.absent();

    private Optional<String> version = Optional.absent();

    private Optional<String> status = Optional.absent();

    private Optional<String> resolution = Optional.absent();

    private Optional<String> operatingSystem = Optional.absent();
    private Optional<String> platform = Optional.absent();

    public Bug(int id) {
        this.id = Optional.of(id);
    }

    /**
     * Creates a {@link Bug} with an absent ID. Typically used when a client is creating a new {@code Bug} to report via
     * {@link BugRepository#create(Bug)}.
     */
    public Bug() {
        this.id = Optional.absent();
    }

    /**
     * @return An {@link Optional} {@code int} identifying this {@link Bug}.
     */
    public Optional<Integer> getId() {
        return id;
    }

    /**
     * @return An {@link Optional} {@code String} describing this {@link Bug bug's} relative priority.
     */
    public Optional<String> getPriority() {
        return priority;
    }

    /**
     * Sets the relative priority of this {@link Bug}.
     * 
     * @param priority A {@code String} describing this bug's priority level.
     */
    public void setPriority(final String priority) {
        this.priority = Optional.fromNullable(priority);
        // this.priority =
    }

    /**
     * @return An {@link Optional} {@code String} describing the relative severity of this {@link Bug}.
     */
    public Optional<String> getSeverity() {
        return severity;
    }

    /**
     * Sets the relative severity of this {@link Bug}.
     * 
     * @param severity A {@code String} describing this bug's severity level.
     */
    public void setSeverity(String severity) {
        this.severity = Optional.fromNullable(severity);
    }

    /**
     * @return An {@link Optional} {@code String} which can be used to uniquely identify this {@link Bug} in place of an ID. Not
     *         available on all Bugzilla installations.
     */
    public Optional<String> getAlias() {
        return alias;
    }

    /**
     * Gives this {@link Bug} a unique alias which can be used in place of an ID. If the alias is not unique, it will be
     * rejected once the bug is updated.
     * 
     * @param alias A {@code String} used to identify this bug.
     */
    public void setAlias(String alias) {
        this.alias = Optional.fromNullable(alias);
    }

    /**
     * @return An {@link Optional} one-line {@code String} describing this {@link Bug}.
     */
    public Optional<String> getSummary() {
        return summary;
    }

    /**
     * Sets the summary for this {@link Bug}.
     * 
     * @param summary A single-line {@code String}.
     */
    // TODO Define behavior when passed a string with line separators
    public void setSummary(String summary) {
        this.summary = Optional.fromNullable(summary);
    }

    /**
     * @return An {@link Optional} {@code Product} this bug belongs to.
     */
    public Optional<Product> getProduct() {
        return product;
    }

    /**
     * Sets the {@link Product} for this {@link Bug}.
     * 
     * @param product A {@code Product} this {@code Bug} belongs to.
     */
    public void setProduct(Product product) {
        this.product = Optional.fromNullable(product);
    }

    /**
     * @return An {@link Optional} {@code String} describing the component this {@link Bug} belongs to.
     */
    public Optional<String> getComponent() {
        return component;
    }

    /**
     * Sets the component for this {@link Bug}.
     * 
     * @param component A {@code String} describing the component this {@code Bug} belongs to.
     */
    public void setComponent(String component) {
        this.component = Optional.fromNullable(component);
    }

    /**
     * Returns a {@code String} representing the product version.
     * 
     * @return An {@link Optional} {@code String} describing the product version this {@link Bug} affects.
     */
    public Optional<String> getVersion() {
        return version;
    }

    /**
     * Sets the product version {@code String} associated with this {@link Bug}. Legal values are dependent on the current
     * {@link Product} associated with this bug, and can be retrieved via
     * {@link ProductRepository#getLegalValuesFor(ProductFields, Product)}.
     * 
     * @param version A {@code String} describing the product version this {@code Bug} affects.
     */
    public void setVersion(String version) {
        this.version = Optional.fromNullable(version);
    }

    /**
     * @return An {@link Optional} {@code String} representing the status of this {@link Bug}.
     */
    public Optional<String> getStatus() {
        return status;
    }

    /**
     * Sets the status {@code String} associated with this {@link Bug}. Legal values are dependent on the installation, and can
     * be retrieved via {@link Bugzilla#getLegalValuesFor(GlobalFields)}.
     * 
     * @param status A {@code String} describing the current status of this {@code Bug}.
     */
    public void setStatus(String status) {
        this.status = Optional.fromNullable(status);
    }

    /**
     * @return An {@link Optional} {@code String} representing the resolution of this {@link Bug}. May be absent depending on
     *         the {@link #status} of the bug.
     */
    public Optional<String> getResolution() {
        return resolution;
    }

    /**
     * Sets the resolution {@code String} associated with this {@link Bug}. Legal values are dependent on the installation, and
     * can be retrieved via {@link Bugzilla#getLegalValuesFor(GlobalFields)}; additionally, resolutions can only be applied when
     * bugs have certain {@link #status statuses}.
     * 
     * @param resolution A {@code String} describing how this {@code Bug} was resolved.
     */
    public void setResolution(String resolution) {
        this.resolution = Optional.fromNullable(resolution);
    }

    /**
     * @return An {@link Optional} {@code String} describing the operating system this {@link Bug} was observed on.
     */
    public Optional<String> getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * Sets the operating system {@code String} this {@link Bug} was observed on. Legal values are dependent on the
     * installation, and can be retrieved via {@link Bugzilla#getLegalValuesFor(GlobalFields)}.
     * 
     * @param operatingSystem A {@code String} describing the operating system where the {@code Bug} was observed.
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = Optional.fromNullable(operatingSystem);
    }

    /**
     * @return An {@link Optional} {@code String} describing the hardware this {@link Bug} was observed on.
     */
    public Optional<String> getPlatform() {
        return platform;
    }

    /**
     * Sets the hardware {@code String} this {@link Bug} was observed on. Legal values are dependent on the installation, and
     * can be retrieved via {@link Bugzilla#getLegalValuesFor(GlobalFields)}.
     * 
     * @param platform A {@code String} describing the hardware where the {@code Bug} was observed.
     */
    public void setPlatform(String platform) {
        this.platform = Optional.fromNullable(platform);
    }
}
