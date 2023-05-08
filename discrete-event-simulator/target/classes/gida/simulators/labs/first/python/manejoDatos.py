
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

###########################################
#Con esto podes importar 
#import subprocess

# Ejecutar el comando pip
#result = subprocess.run(['python', '-m', 'pip', 'install', 'seaborn'], capture_output=True)

# Imprimir la salida del comando pip
#print(result.stdout)
###########################################
df = pd.read_csv('discrete-event-simulator\\src\\main\\java\\gida\\simulators\\labs\\results\\report.csv')
df.drop(df.columns[df.columns.str.contains('Tiempo de simulacion',case = False)],axis = 1, inplace = True)
df.drop(df.columns[df.columns.str.contains('Tiempo total de transito',case = False)],axis = 1, inplace = True)
df.drop(df.columns[df.columns.str.contains('Tiempo total de espera en cola',case = False)],axis = 1, inplace = True)
df.drop(df.columns[df.columns.str.contains('Tiempo total de ocio de la pista',case = False)],axis = 1, inplace = True)
df.drop(df.columns[df.columns.str.contains('Cantidad de aeronaves que aterrizaron',case = False)],axis = 1, inplace = True)
print(df.head())
plt.figure(figsize=(10, 6))


g = sns.catplot(
    data=df ,
    capsize=.1, palette="YlGnBu_d", errorbar="se",
    kind="point", height=6, aspect=3.)
plt.xticks(rotation=45)
g.despine(left=True)
# ahora lo guardo en una imagen
plt.savefig('discrete-event-simulator\\src\\main\\java\\gida\\simulators\\labs\\results\\report.png', dpi=300, bbox_inches='tight')