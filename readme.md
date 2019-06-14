Dans le fichier readMe :
Afficher des captures d'écrans "Kind" des google datastores utilisés

Question contrôle : 
 - Ecrire les entités et les instanciées un fois :
    class Pet 
     Kind: Pet
      Key: UniqueId
     Owner: string
     sign: List<string>
    nb: Int
    
    e = n.Entity(Pet, "Pet1")
    e.setproperty(Owner) ="u1"
    e.setproperty(sign) = ["U2,U4,U6"]

 - Afficher les pétitions avec une requête gql pour lequels j'ai voté
    Select * from Pet
        Where Sign="Me"
 - Afficher les pétitions les plus populaires avec une requête gql 
    Select * from Pet
        order by Asc nb
        unit 100
 - Pour calculer le speedup d'ajouté des processeurs :
    Loi d'Amdahl
 - Ecrire le server REST appelé par le contrôleur   
   @API    
    Class PetEndPoint
        Method
        Top100() {
            d = factory.getStore()
            q = d.query()
            iter = q.fetch()
            return iter;
        }
 - Lors d'un accès en zig zag merge join on va directement à la bonne clé dans chaque colonne
    Le zig zag merge join ne fonctionne qu'avec les égalités
 - Pour le sharding -> hybride mais pour google datastore -> horizontal
 ACID -> atomic constitancy durability -> si oui alors garantie l'ordre d'arrivé et de fin des transactions dans le datastore
Les variables de sessions du conteneur de servlet sont stockés