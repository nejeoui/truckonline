# Test Java 10 - JAX-RS Truckonline

## Objectifs

Implémenter un service permettant de calculer les amplitudes d'un conducteur à partir d'une liste d'activités.  
Un test unitaire est inclus avec le projet: l'objectif est atteint quand le test unitaire passe.

Définitions:

  - une activité conducteur représente une période de temps avec un type décrivant l'occupation du conducteur.
      le type peu être :

      - DIVING: conduite
      - WORK: travail
      - AVAILABILITY: mise à disposition
      - OTHER: temps autre non qualifiés
      - REST: repos

  - une amplitude de service, est une période de temps regroupant une série d'activités, dont le type peu être :
  
      - DRIVING
      - WORK
      - AVAILABILITY
      - OTHER

      et toute activité de repos (REST) dont le cumul de temps est inférieur à 9 heures.
      Une amplitude de service ne peu pas commencer ni se terminer par du repos (activité de type REST)

  - une amplitude de coupure, est une période de temps comprenant du repos ( activité de type REST ) ou aucune activité, ayant une durée
      totale supérieure ou égale à 9 heures.

Résultat attendu:

  - le service implémenté (ExamService) doit retourner une liste d'objets AmplitudeInfo représentant les amplitudes conducteurs.
    les cumuls doivent être calculés.
    par exemple :
       amplitude 1 : type=service date_début="2019-07-08T05:12:00Z" date_fin="2019-07-08T10:45:00Z" totalRestMin=56 ...
       amplitude 2 : type=repos   date_début="2019-07-08T10:45:00Z" date_fin="2019-07-08T13:55:00Z" totalRestMin=3 ...
       ...

Objets en entrée/sortie du service :

    - DriverActivityInfo ( package com.truckonline.api.exam.dto )

        DRIVER_ACTIVITY activity : activité conducteur (DRIVING, REST, ...)
        Instant startDate : date de début de l'activité
        Instant endDate : date de fin de l'activité
        Integer startKm : kilométrage en début d'activité
        Integer endKm : kilométrage en fin d'activité
        String driverUid : identifiant unique du conducteur
        String vehicleUid : identifiant unique du véhicule

    - AmplitudeInfo ( package com.truckonline.api.exam.dto )

        Instant startDate : date de début de l'amplitude
        Instant endDate : date de fin de l'amplitude
        AMPLITUDE_TYPE type : type de l'amplitude ( SERVICE_PERIOD ou LONG_BREAK_PERIOD )
        Long totalDrivingMin : total des temps des activités DRIVING dans l'amplitude en minutes
	      Long totalRestMin : total des temps des activités REST dans l'amplitude en minutes
	      Long totalWorkMin : total des temps des activités WORK dans l'amplitude en minutes
	      Long totalAvailabilityMin : total des temps des activités AVAILABILITY dans l'amplitude en minutes
	      Long totalOtherMin : total des temps des activités OTHER dans l'amplitude en minutes
	      Long totalNoDataMin : total des temps sans activité ( temps entre deux activités ) dans l'amplitude en minutes
	      Long totalServiceMin : total des temps de services ( DRIVING, WORK, AVAILABILITY, OTHER ) dans l'amplitude en minutes
        Long totalKm : cumul des endKm-startKm des activités de l'amplitude

## Instructions

- Le service doit prendre en entrée une liste de `DriverActivityInfo`, et doit génèrer en sortie une liste de `AmplitudeInfo`.
- Ignorer les activités de repos (`REST`) en début et fin de période.

## Test du code

- un test Arquillian est présent dans le projet, pour l'executer :

  $ mvn clean test

## Glossaire

- Type d'activité: `DRIVING` (conduite), `WORK` (travail), `AVAILABILITY` (mise à disposition), `OTHER` (autre), `REST` (repos).
- Temps de service: toute activité hors `REST`.
- Type d'amplitude: `SERVICE_PERIOD` (amplitude de service), `LONG_BREAK_PERIOD` (amplitude de repos).
