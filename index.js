/**
 * Triggered from a change to a Cloud Storage bucket.
 *
 * @param {!Object} event Event payload.
 * @param {!Object} context Metadata for the event.
 */
 const axios = require("axios")
 exports.helloGCS = async(event, context) => {
   const gcsEvent = event;
   /*
   Create URL so ML model can access the new file on Cloud Storage Bucket
   no whitespace on fileName please
   */
   const baseURL = "https://storage.cloud.google.com/test-bucket-29218219/"
   const fileName = gcsEvent.name
   const finalURL = baseURL + fileName
   console.log(finalURL)
 
   /*
   Tensorflow.js
   expected to return a JSON
   */
 
 
   /* 
   Dummy JSON for HTTP POST request 
   Change to prediction result from ML model on deployment
   */
   await axios.post("https://enduring-badge-311503.et.r.appspot.com/add",{
     "id" : "2312312fdqwd",
     "motif1":{
         "motifName": "Kawung",
         "value": 75
             },
     "motif2":{
         "motifName": "Parang",
         "value": 21
             },
     "motif3":{
         "motifName": "Megamendung",
         "value": 3
             }
     })
 };
 