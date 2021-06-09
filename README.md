# Machine Learning

## 1. Batik Making Technique Classification

### INTRODUCTION
Batik is an Indonesian technique of wax-resist dyeing applied to the whole cloth. This technique originated from the island of Java, Indonesia. Batik is made either by drawing dots and lines of the resist with a spouted tool called a canting, or by printing the resist with a copper stamp called a cap. The applied wax resists dyes and therefore allows the artisan to colour selectively by soaking the cloth in one colour, removing the wax with boiling water, and repeating if multiple colours are desired.<br>

At this time, batik have 2 main making techniques. There are:
1. Batik Tulis (Hand-writen Batik)
2. Batik Cetak (Printed Batik)

Batik Tulis or Hand-written Batik has a relatively expensive price because the manufacturing process is complicated and has meaning in each motif. While Batik Cetak (Printed Batik) can be mass-produced, and the motif is printed using a machine so that it can be finished quickly, therefore the price is much cheaper.

But sometimes it is difficult to distinguish written batik from printed batik because they have similar motifs, so sometimes price fraud occurs in batik transactions.

Therefore, this project is built to distinguish hand-written and printed batik using Deep Learning Technology. So we can classify the batik fabric image whether it is hand-written or printed batik.

### TECHNOLOGIES
This project is built using Deep Learning Technology with Convolutional Neural Network algorithm and Transfer Learning. Here are the library that used in this project.
- Python 3.6.9
- TensorFlow 2.4.1
- NumPy 1.20.0
- Matplotlib 3.4.2

### LAUNCH
Inside batik-technique-classification folder, there are some files:
- [batik-technique-classification_(without_transfer_learning).ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/batik-technique-classification_(without_transfer_learning).ipynb)
- [batik-technique-classification_(using_mobilenet).ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/batik_technique_classification_(using_mobilenet).ipynb)
- [batik-technique-classification_(using_xception).ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/batik_technique_classification_(using_xception).ipynb)
- [test_model.ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/test_model.ipynb)



## 2. Batik Motif Classification
Consisting 5 motifs batik to classify, including :
1. Ceplok
2. Kawung
3. Parang
4. Megamendung 
5. Sidomukti

### DATASET
After we collected dataset manually from various sources, we got total 3.118 datasets. The distribution as follows :
- Ceplok's dataset : 708
- Kawung's dataset : 661
- Parang's dataset : 922
- Megamendung's dataset : 560
- Sidomukti's dataset : 267

### TRANSFER LEARNING
We use Convolutional Neural Network and the base model from the InceptionV3 pre-trained model developed at Google on Imagenet Dataset

### SUPPORTED BY 
Tensorflow : 2.5.0

### LAUNCH
Fix model that we use [colab_image_classification_inceptionv3.ipynb](https://github.com/farrel25/naratik/blob/machine-learning/batik-motif-classification/colab_image_classification_inceptionv3.ipynb)

