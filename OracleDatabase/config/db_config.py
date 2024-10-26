import os

ORACLE_DATABASE_URL = os.getenv('ORACLE_DATABASE_URL', 'oracle://username:password@hostname:port/service_name')
