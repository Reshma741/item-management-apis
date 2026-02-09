package com.example.items.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.items.entity.Item;
import com.example.items.excpetion.ResourceNotFoundException;
import com.example.items.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
 private ItemRepository itemRepository;
	
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	public Item findItemById(Long id) {
		return itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item not found With id "+id));
	}
	
	
	public Page<Item> getAllItems(int pageNo,int size){
		Pageable pageable= PageRequest.of(pageNo, size,Sort.by("id").ascending());
		return itemRepository.findAll(pageable);
		
		
	}
	
	public Item upadteItemById(Item item,Long id) {
		Item updatedItem=findItemById(id);
		updatedItem.setName(item.getName());
        updatedItem.setPrice(item.getPrice());
        updatedItem.setDescription(item.getDescription());
        
        return itemRepository.save(updatedItem);
	}
	
	
	public void deleteItemById(Long id) {
		
	   Optional<Item> optional=itemRepository.findById(id);
	   if(optional.isPresent()) {
		   itemRepository.deleteById(id);
		   
	   }
	   else {
		   throw new ResourceNotFoundException("ResorceNot Found");
	   }
		
	}

}
