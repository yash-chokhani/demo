package com.example.demo.service;

import com.example.demo.dao.AreaDocumentRepo;
import com.example.demo.models.AreaDocument;
import com.example.demo.models.AreaResponse;
import com.example.demo.models.JsonPojo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.DataFormatException;

@Service
public class AreaService {

    private final AreaDocumentRepo areaDocumentRepo;

    public AreaService(AreaDocumentRepo areaDocumentRepo) {
        this.areaDocumentRepo = areaDocumentRepo;
    }

    public AreaResponse getAreaTask(Integer id){
        AreaDocument areaDocument = areaDocumentRepo.findById(id).get();
        return new AreaResponse(areaDocument.getId(), areaDocument.getArea());
    }

    public List<AreaResponse> getArea(Map<Integer, JsonPojo> areaPojo) throws DataFormatException {
        List<AreaResponse> areaResponses = new ArrayList<>();
        for (Map.Entry<Integer, JsonPojo> jsonPojoEntry: areaPojo.entrySet()){
            JsonPojo jsonPojo= jsonPojoEntry.getValue();
            if(jsonPojo.getType().equalsIgnoreCase("circle")){
                if (Objects.isNull(jsonPojo.getRadius()))
                    throw new DataFormatException("side cannot be empty for circle");
                getCircleArea(jsonPojoEntry.getKey(), jsonPojo.getRadius(), areaResponses);
            }
            else if(jsonPojo.getType().equalsIgnoreCase("rectangle")){
                if (Objects.isNull(jsonPojo.getLength()) || Objects.nonNull(jsonPojo.getBreadth()))
                    throw new DataFormatException("side cannot be empty for rectangle");
                getRectangleArea(jsonPojoEntry.getKey(), jsonPojo.getLength(), jsonPojo.getBreadth(), areaResponses);
            }
            else if (jsonPojo.getType().equalsIgnoreCase("square")){
                if (Objects.isNull(jsonPojo.getSide()))
                    throw new DataFormatException("side cannot be empty for square");
                getSquareArea(jsonPojoEntry.getKey(), jsonPojo.getSide(), areaResponses);
            }
        }
        List<AreaDocument> areaDocumentList = new ArrayList<>();
        areaResponses.forEach(
                areaResponse -> {
                    AreaDocument areaDocument = new AreaDocument(areaResponse.getId(), areaResponse.getArea());
                    areaDocumentList.add(areaDocument);
                }
        );
        areaDocumentRepo.saveAll(areaDocumentList);
        return areaResponses;
    }

    private void getCircleArea(int id ,double radius, List<AreaResponse> areaResponses){
        double area = ((22)*(radius)*(radius)/7);
        AreaResponse areaResponse = new AreaResponse(id,area);
        areaResponses.add(areaResponse);
    }

    private void getRectangleArea(int id ,double length, double breadth, List<AreaResponse> areaResponses){
        double area = length*breadth;
        AreaResponse areaResponse = new AreaResponse(id,area);
        areaResponses.add(areaResponse);
    }

    private void getSquareArea(int id ,double side, List<AreaResponse> areaResponses){
        double area = side*side;
        AreaResponse areaResponse = new AreaResponse(id,area);
        areaResponses.add(areaResponse);
    }

}
