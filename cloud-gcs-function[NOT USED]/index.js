/**
 * Triggered from a change to a Cloud Storage bucket.
 *
 * @param {!Object} event Event payload.
 * @param {!Object} context Metadata for the event.
 */
const axios = require("axios")
const TeachableMachine = require("@sashido/teachablemachine-node")
const model = new TeachableMachine({
    modelUrl:"https://storage.googleapis.com/ml-model-86088/my_model/"
});
const predictionResult = []
exports.helloGCS = async(event, context) => {
    const gcsEvent = event
    /*
    Create URL so ML model can access the new file on Cloud Storage Bucket
    no whitespace on fileName please
    */
    const baseURL = "https://storage.googleapis.com/test-bucket-29218219/"
    const fileName = gcsEvent.name
    const finalURL = baseURL + fileName

    /*
    Tensorflow.js
    expected to return a JSON
    */
    await model.classify({
        imageUrl: finalURL,
    }).then((predictions) => {
        predictionResult.push(predictions)
        console.log("Predictions:", predictions)
    }).catch((e) => {
        console.log("ERROR", e)
    });
    /*
    Dummy JSON for HTTP POST request
    Change to prediction result from ML model on deployment
    */
    /*
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
         })*/
    /*
    Post Top 3 prediction result to database
     */
    await axios.post("https://enduring-badge-311503.et.r.appspot.com/add",{
        "id" : "testtf1234",
        "motif1":{
            "motifName": predictionResult[0][0].class,
            "value": predictionResult[0][0].score
        },
        "motif2":{
            "motifName": predictionResult[0][1].class,
            "value": predictionResult[0][1].score
        },
        "motif3":{
            "motifName": predictionResult[0][2].class,
            "value": predictionResult[0][2].score
        }
    })

};
