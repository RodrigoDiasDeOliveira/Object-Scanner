import tensorflow as tf
import numpy as np
import cv2

class ObjectDetector:
    def __init__(self, model_path="model.tflite"):
        self.interpreter = tf.lite.Interpreter(model_path=model_path)
        self.interpreter.allocate_tensors()

    def detect(self, image):
        input_tensor_index = self.interpreter.get_input_details()[0]['index']
        output_tensor_index = self.interpreter.get_output_details()[0]['index']
        
        image_resized = cv2.resize(image, (224, 224))
        input_data = np.expand_dims(image_resized, axis=0).astype(np.float32)

        self.interpreter.set_tensor(input_tensor_index, input_data)
        self.interpreter.invoke()
        output_data = self.interpreter.get_tensor(output_tensor_index)

        return output_data
