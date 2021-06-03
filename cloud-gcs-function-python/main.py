import requests
def predict_motif(event, context):
    """Triggered by a change to a Cloud Storage bucket.
    Args:
         event (dict): Event payload.
         context (google.cloud.functions.Context): Metadata for the event.
    """
    file = event
    print(f"Processing file: {file['name']}.")
    BASE_URL = "https://causal-folder-315209.et.r.appspot.com/"
    FILE_NAME = file["name"]
    FINAL_URL = BASE_URL + FILE_NAME
    requests.get(FINAL_URL, headers={"Auth": <Super_Secret_Key})

