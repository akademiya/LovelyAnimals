package com.gvd.lovelyanimals.data

class PetModel {
    var pid: String? = null
    var petPhoto: String? = null
    var petTitle: String? = null

    constructor(pid: String?, petTitle: String?, pet_photo: String) {
        this.pid = pid
        this.petTitle = petTitle
        this.petPhoto = pet_photo
    }

    constructor() {}
}