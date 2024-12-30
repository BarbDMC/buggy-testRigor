package com.testrigor;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ResultTest {

    private Result result;

    @BeforeEach
    public void setUp() {
        result = mock(Result.class);
    }

    @Test
    public void testCollectUniquePeopleNamesFromApiResponse() throws IOException {
        String mockApiResponse = "<Response><data>" +
                "<person><name>James Gatsby</name><age>33</age></person>" +
                "<person><name>Emery Hillr</name><age>41</age></person>" +
                "<person><name>James Gatsby</name><age>29</age></person>" +
                "</data></Response>";

        when(result.callPeopleApi()).thenReturn(mockApiResponse);

        Collection<String> uniqueNames = result.collectUniquePeopleNamesFromApiResponse();

        assertEquals(2, uniqueNames.size(), "There should be two unique names");
        assertEquals(true, uniqueNames.contains("James Gatsby"));
        assertEquals(true, uniqueNames.contains("Emery Hillr"));
    }

    @Test
    public void testEmptyApiResponse() throws IOException {
        String mockEmptyApiResponse = "<Response><data></data></Response>";

        when(result.callPeopleApi()).thenReturn(mockEmptyApiResponse);

        Collection<String> uniqueNames = result.collectUniquePeopleNamesFromApiResponse();

        assertEquals(0, uniqueNames.size(), "The unique names collection should be empty");
    }
}
