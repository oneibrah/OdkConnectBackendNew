package com.odk.connect.controller;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odk.connect.exception.ExceptionHandling;
import com.odk.connect.exception.model.PromotionException;
import com.odk.connect.model.HttpResponse;
import com.odk.connect.model.LignePromotion;
import com.odk.connect.service.LignePromotionService;
import com.odk.connect.service.PromotionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = { "/", "/odkConnect/lignePromo" })
@RequiredArgsConstructor
public class LignePromotionController extends ExceptionHandling{
	private final LignePromotionService lignePromoService;
	@PostMapping("/saveLignePromo")
	ResponseEntity<LignePromotion> save(@RequestBody LignePromotion lignePromo) throws PromotionException{
		return ResponseEntity.ok(lignePromoService.save(lignePromo));
	}
	@PutMapping("/updateLignePromo/{idLignePromo}/{idPromo}/{idUser}")
	ResponseEntity<LignePromotion> update(@PathVariable("idLignePromo") Long idLignePromo,@PathVariable("idPromo") Long idPromo,@PathVariable("idUser") Long idUser) throws PromotionException{
		return ResponseEntity.ok(lignePromoService.update(idLignePromo, idPromo, idUser));
	}
	@GetMapping("/lignePromotions/{idLignePromo}")
	ResponseEntity<LignePromotion> findByLignePromoById(@PathVariable("idLignePromo") Long idLignePromo) {
		return ResponseEntity.ok(lignePromoService.findByLignePromoById(idLignePromo));
	}
	@GetMapping("/lignePromos")
	ResponseEntity<List<LignePromotion>> findAllLignePromo(){
		return ResponseEntity.ok(lignePromoService.findAllLignePromo());
	}
	@GetMapping("/user/lignePromotions/{idUser}")
	ResponseEntity<List<LignePromotion>> findHistoriquePromotion(@PathVariable("idUser") Long idUser) throws PromotionException {
		return ResponseEntity.ok(lignePromoService.findHistoriquePromotion(idUser));
	}
	@GetMapping("/promotion/lignePromotions/{idPromo}")
	ResponseEntity<List<LignePromotion>> findAllLignesPromotionByPromotionId(@PathVariable("idPromo") Long idPromo) throws PromotionException {
		return ResponseEntity.ok(lignePromoService.findAllLignesPromotionByPromotionId(idPromo));
	}
	@DeleteMapping("deleteLignePromo")
	@PreAuthorize("hasAnyAuthority('promotion:delete')")
	ResponseEntity<HttpResponse> deleteLignePromo(Long id) {
		lignePromoService.deleteLignePromo(id);
		return response(HttpStatus.OK, "ligne promotion supprimé avec succès");
		
	}
	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		HttpResponse body = new HttpResponse(new Date(), httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase());
		return new ResponseEntity<HttpResponse>(body, httpStatus);
	}

}