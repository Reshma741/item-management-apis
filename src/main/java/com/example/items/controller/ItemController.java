package com.example.items.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.items.entity.Item;
import com.example.items.service.ItemService;


@RestController
@RequestMapping("/items/apis")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	
	
	
	@PostMapping("/save")
	public ResponseEntity<Item> saveItem(@RequestBody Item item){
		Item items= itemService.saveItem(item);
		return new ResponseEntity<Item>(items, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findItem/{id}")
	public ResponseEntity<Item> findItem(@PathVariable Long id){
		return  new ResponseEntity<Item>(itemService.findItemById(id), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/updateItems/{id}")
	public ResponseEntity<Item> updateItems(@RequestBody Item item,@PathVariable Long id){
		Item items=itemService.upadteItemById(item, id);
		return new ResponseEntity<Item>(items, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/deleteItem/{id}")
	public ResponseEntity<?> deleteItemById(@PathVariable Long id){
		itemService.deleteItemById(id);
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}
	
	
	
	@GetMapping("/all")
	public Page<?> getAll(@RequestParam(defaultValue  ="0") int pageNo,@RequestParam(defaultValue = "5") int size){
		return itemService.getAllItems(pageNo, size);
	}
	
	

}
