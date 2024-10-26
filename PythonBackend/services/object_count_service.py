from utils.image_processing import analyze_image

class ObjectCountService:

    def process_image(self, image):
        # Analisar a imagem e contar objetos
        result = analyze_image(image)
        return result
