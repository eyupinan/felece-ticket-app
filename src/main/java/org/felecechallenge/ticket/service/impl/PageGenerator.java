package org.felecechallenge.ticket.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageGenerator {
    public static Pageable generate(String sortby,Integer offset,Integer limit){
        if (offset==null){
            offset=0;
        }
        if (limit==null){
            offset=0;
        }
        Pageable firstPageWithTwoElements;
        if (sortby!=null && limit!=null && offset!=null){
            Sort sort_obj = Sort.by(Sort.Direction.ASC,sortby);
            firstPageWithTwoElements = PageRequest.of(offset/limit,limit,sort_obj);
        }
        else if (limit!=null && offset!=null){
            firstPageWithTwoElements = PageRequest.of(offset/limit, limit);
        }
        else if (sortby!=null){
            Sort sort_obj = Sort.by(Sort.Direction.ASC, sortby);
            firstPageWithTwoElements = PageRequest.of(offset, Integer.MAX_VALUE, sort_obj);
        }
        else{
            firstPageWithTwoElements = PageRequest.of(offset, Integer.MAX_VALUE);
        }
        return firstPageWithTwoElements;
    }
}
