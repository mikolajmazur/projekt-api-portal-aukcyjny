export class Category {
  id: number;
  name: string;
  subCategories: Category[];
  parentCategories: Category[];
}
