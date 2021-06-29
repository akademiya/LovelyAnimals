package com.gvd.lovelyanimals

object ImageUtils {
//    private val storageInstance: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
//
//    private val currentUserRef: StorageReference
//        get() = storageInstance.reference
//            .child(
//                FirebaseAuth.getInstance().currentUser?.uid
//                ?: throw NullPointerException("UID is null")
//            )
//
//    fun uploadOwnerPhoto(imageBytes: ByteArray, onSuccess: (imagePath: String) -> Unit) {
//        val reference = currentUserRef.child("ownerPhoto/${UUID.nameUUIDFromBytes(imageBytes)}")
//        reference
//            .putBytes(imageBytes)
//            .addOnSuccessListener { onSuccess(reference.path) }
//    }
//
//    fun uploadPetPhoto(imageBytes: ByteArray, onSuccess: (imagePath: String) -> Unit) {
//        val reference = currentUserRef.child("petPhoto/${UUID.nameUUIDFromBytes(imageBytes)}")
//        reference
//            .putBytes(imageBytes)
//            .addOnSuccessListener { onSuccess(reference.path) }
//    }
//
//    fun removePetPhoto(imageUri: String) {
//        val reference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUri)
//        reference.delete()
//    }
//
//    fun pathToReference(path: String) = storageInstance.getReference(path)
}