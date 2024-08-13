package com.brendan.wordfinder.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.brendan.wordfinder.WordFinder;
import com.brendan.wordfinder.exception.IllegalGridLocationException;

/**
 * A REST controller to return the word finder data as JSON.
 * 
 * @author Brendan Douglas
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    /**
     * @return
     * @throws IllegalGridLocationException
     */
    @CrossOrigin(origins = "http://localhost:3000") // TODO update - don't hardcode.
    @PostMapping(value = "/wordfinder", produces = "application/json")
    public String generateGrid(@RequestBody WordFinderRequest request) throws IllegalGridLocationException {
        WordFinder wf = new WordFinder(request.getRows(), request.getColumns());

        return wf.generate(request.getWords());
    }

}
