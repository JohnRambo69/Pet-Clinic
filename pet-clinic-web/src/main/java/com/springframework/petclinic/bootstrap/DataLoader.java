package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedRadiology = specialtyService.save(radiology);
        Speciality savedDentistry = specialtyService.save(dentistry);
        Speciality savedSurgery = specialtyService.save(surgery);

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

        Visit catVisit = new Visit();
        catVisit.setPet(samCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty.");
        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("John");
        vet1.setLastName("Cash");
        vet1.getSpecialities().add(savedDentistry);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Barbra");
        vet2.setLastName("Streisand");
        vet2.getSpecialities().add(savedRadiology);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
