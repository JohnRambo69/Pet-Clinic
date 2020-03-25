package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetTypeService;
import com.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Alex");
        owner1.setLastName("Raid");
        owner1.setAddress("123 Baker Street");
        owner1.setCity("London");
        owner1.setTelephone("12348855");

        Pet alexPet = new Pet();
        alexPet.setName("Roki");
        alexPet.setPetType(saveDogPetType);
        alexPet.setOwner(owner1);
        alexPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(alexPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Sam");
        owner2.setLastName("Wesley");
        owner2.setAddress("123 Baker Street");
        owner2.setCity("London");
        owner2.setTelephone("12348855");

        Pet samCat = new Pet();
        samCat.setName("Ketka");
        samCat.setPetType(saveCatPetType);
        samCat.setOwner(owner2);
        samCat.setBirthDate(LocalDate.now());
        owner2.getPets().add(samCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("John");
        vet1.setLastName("Cash");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Barbra");
        vet2.setLastName("Streisand");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
