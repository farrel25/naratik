import requests
def predict_motif(event, context):
    """Triggered by a change to a Cloud Storage bucket.
    Args:
         event (dict): Event payload.
         context (google.cloud.functions.Context): Metadata for the event.
    """
    file = event
    print(f"Processing file: {file['name']}.")
    BASE_URL = "<prediction endpoint>"
    FILE_NAME = file["name"]
    FINAL_URL = BASE_URL + FILE_NAME
    requests.get(FINAL_URL, headers={"Auth": <SUPER_SECRET_KEY>}

