package com.example.resourceserver;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/houses")
public class HouseController {

    public final static List<House> houses;

    static {
        houses = new ArrayList<>();
        houses.add(new House("Lannister"));
        houses.add(new House("Stark"));
        houses.add(new House("Targaryen"));
        houses.add(new House("Martell"));
        houses.add(new House("Greyjoy"));
        houses.add(new House("Baratheon"));
    }

    @GetMapping
    public List<House> getHouses() {
        return houses;
    }

    @PostMapping("/{newHouse}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') and #oauth2.hasScope('toll_read')")
    public List<House> addHouse(@PathVariable String newHouse) {
        houses.add(new House(newHouse));
        return houses;
    }
}
