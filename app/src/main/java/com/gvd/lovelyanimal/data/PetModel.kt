package com.gvd.lovelyanimal.data

import android.net.Uri

class PetModel {
    var pid: String? = null
    var petPhoto: String? = null
    var petTitle: String? = null
    var petDescription: String? = null

    constructor(pid: String?, petTitle: String?, petDescription: String?, pet_photo: String) {
        this.pid = pid
        this.petTitle = petTitle
        this.petDescription = petDescription
        this.petPhoto = pet_photo
    }

    constructor(petTitle: String, petDescription: String, pet_photo: String) {
        this.petTitle = petTitle
        this.petDescription = petDescription
        this.petPhoto = pet_photo
    }

    constructor(petTitle: String, petDescription: String) {
        this.petTitle = petTitle
        this.petDescription = petDescription
    }

    constructor() {}
}