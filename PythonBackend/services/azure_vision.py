from services.azure_service import AzureService

class AzureVision:
    def __init__(self):
        self.azure_service = AzureService()

    def analyze_and_count_objects(self, image):
        result = self.azure_service.analyze_image(image)

        # Aqui você pode implementar a lógica para contar objetos com base na resposta
        # Por exemplo, você pode analisar a propriedade 'categories' ou 'description'

        object_counts = {}
        if 'categories' in result:
            for category in result['categories']:
                object_name = category['name']
                confidence = category['score']
                object_counts[object_name] = {
                    'confidence': confidence
                }

        return object_counts
