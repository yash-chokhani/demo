package com.example.demo.controller;


import com.example.demo.models.AreaResponse;
import com.example.demo.models.GenericResponse;
import com.example.demo.models.JsonPojo;
import com.example.demo.service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/area")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/results")
    public ResponseEntity<GenericResponse> getArea(@RequestBody Map<Integer, JsonPojo> areaPojo){
        try {
            List<AreaResponse> areaResponseList = areaService.getArea(areaPojo);
            return new ResponseEntity<>(new GenericResponse(true, null, areaResponseList), HttpStatus.OK);
        } catch (DataFormatException e){
            return new ResponseEntity<>(new GenericResponse(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new GenericResponse(false, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/results/{task_id}")
    public ResponseEntity<GenericResponse> getAreaTask(@PathVariable Integer task_id){
        try{
            AreaResponse areaResponse = areaService.getAreaTask(task_id);
            return new ResponseEntity<>(new GenericResponse(true, null, areaResponse), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new GenericResponse(false, "no data for this id", null), HttpStatus.NOT_FOUND);
        }
    }

}
