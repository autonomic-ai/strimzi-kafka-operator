/*
 * Copyright Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.crdgenerator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;

import static io.strimzi.crdgenerator.DocGenerator.classInherits;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DocGeneratorTest {

    @Test
    public void simpleTest() throws IOException, ClassNotFoundException, URISyntaxException {
        assertThat(classInherits(Class.forName("io.strimzi.crdgenerator.KubeLinker"), Linker.class), is(notNullValue()));
        StringWriter w = new StringWriter();
        DocGenerator crdGenerator = new DocGenerator(1, singletonList(ExampleCrd.class), w, new KubeLinker("{KubeApiReferenceBase}"));
        crdGenerator.generate(ExampleCrd.class);
        String s = w.toString();
        assertThat(CrdTestUtils.readResource("simpleTest.adoc"), is(s));
    }
}
