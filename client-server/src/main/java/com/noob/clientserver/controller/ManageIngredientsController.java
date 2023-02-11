package com.noob.clientserver.controller;

import com.noob.clientserver.service.IngredientService;
import com.noob.commons.model.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/ingredients")
public class ManageIngredientsController {
  
  private final IngredientService ingredientService;

  public ManageIngredientsController(IngredientService ingredientService) {
    this.ingredientService = ingredientService;
  }

  @GetMapping
  public ResponseEntity<Iterable<Ingredient>> ingredientsAdmin() {
    return ResponseEntity.ok(ingredientService.findAll());
  }
  
  @PostMapping
  public ResponseEntity<Ingredient> addIngredient(Ingredient ingredient) {
    return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
  }
}
