/*
 * MIT License
 *
 * Copyright (c) 2022-2023 Volodya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.lombrozo.testnames;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * The project without JUnit extensions.
 *
 * @since 0.1.17
 */
public class ProjectWithoutJUnitExtensions implements Project {

    /**
     * Original project.
     */
    private final Project original;

    /**
     * Constructor.
     * @param original Original project.
     */
    ProjectWithoutJUnitExtensions(final Project original) {
        this.original = original;
    }

    @Override
    public Collection<ProductionClass> productionClasses() {
        return this.original.productionClasses();
    }

    @Override
    public Collection<TestClass> testClasses() {
        return Collections.unmodifiableCollection(
            this.original.testClasses().stream()
                .filter(this::isNotJUnitExtension)
                .collect(Collectors.toList())
        );
    }

    /**
     * Is JUnit extension.
     * @param testClass Test class.
     * @return True if JUnit extension.
     */
    private boolean isNotJUnitExtension(final TestClass testClass) {
        return testClass.parents()
            .stream()
            .map(Class::getPackage)
            .map(Package::getName)
            .noneMatch(name -> name.startsWith("org.junit.jupiter.api.extension"));
    }
}