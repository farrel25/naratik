const express = require('express');
const router = express.Router();
const fire = require("./fire");
const db = fire.firestore();


/*
    This HTTP POST needs JSON as its request body
    format:
    {
    "id" : <unique ID>,
    "motif1":{
        "motifName": <Motif Name 1>,
        "value": <Prediction Value of Motif1>
            },
    "motif2":{
        "motifName": <Motif Name 2>,
        "value": <Prediction Value of Motif2>
            },
    "motif3":{
        "motifName": <Motif Name 3>,
        "value": <Prediction Value of Motif3
            }
    }
 */

router.post('/', async (req,res)=>{
    const predictionResult = req.body

    try{
        // Upload data to firestore
        await db.collection('motif')
            .doc(predictionResult.id)
            .set({
                "motif1":{
                    "motifName": predictionResult.motif1.motifName,
                    "value": predictionResult.motif1.value
                },
                "motif2":{
                    "motifName": predictionResult.motif2.motifName,
                    "value": predictionResult.motif2.value
                },
                "motif3":{
                    "motifName": predictionResult.motif3.motifName,
                    "value": predictionResult.motif3.value
                }
            })
            .then(()=>{
                res.send(predictionResult)

            })
    }catch (e) {
        res.send("Upload Fail")
    }
})

module.exports = router;
