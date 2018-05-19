/*
 * Copyright 2016 the original author or authors.
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
package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testAddAPerson() throws Exception {
        this.mockMvc.perform(get("/person/add").param("name", "Selim")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Selim"));
    }

    @Test
    public void testAddAPersonWithADefaultName() throws Exception {
        this.mockMvc.perform(get("/person/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Person 1"));

        this.mockMvc.perform(get("/person/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Person 2"));
    }


    @Test
    public void testListAllPerson() throws Exception {
        //No person
        this.mockMvc.perform(get("/person/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));


        //With 3 persons
        this.mockMvc.perform(get("/person/add").param("name", "Selim1"));
        this.mockMvc.perform(get("/person/add").param("name", "Selim2"));
        this.mockMvc.perform(get("/person/add").param("name", "Selim3"));
        this.mockMvc.perform(get("/person/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }


}
